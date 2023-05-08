$(document).ready(function () {
    $('#country-table').DataTable({
        ajax: {
            url: 'api/country',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'code'
            },
            {
                data: 'name'
            },
            {
                data: 'region.name'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-country"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-country"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="countryDelete(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    
                    `;
                }
            }

        ]
    });

    $.ajax({
        method: "GET",
        url: "api/region",
        dataType: "JSON",
        success: function (result) {
            $.each(result, function(key, value) {
                $('.country-in-region').append(
                    '<option value="'
                                 + value.id
                                 + '">'
                                 + value.name
                                 + '</option>'
                ),
                $('.country-up-region').append(
                    '<option value="'
                                 + value.id
                                 + '">'
                                 + value.name
                                 + '</option>'
                )
            })
        }
    });

});

function detail(id) {
    $.ajax({
        method: "GET",
        url: "api/country/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#country-det-id').val(`${result.id}`)
            $('#country-det-code').val(`${result.code}`)
            $('#country-det-name').val(`${result.name}`)
            $('#country-det-region').val(`${result.region.name}`)
        }
    })
}

function create() {
    let valCode = $('#country-in-code').val();
    let valName = $('#country-in-name').val();
    let valRegion = $('.country-in-region').val();
    $.ajax({
        method: "POST",
        url: "api/country",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            code: valCode,
            name: valName,
            region: {
                id: valRegion
            }
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-country').modal('hide')
            $('#country-table').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Successfully to insert',
                showConfirmButton: false,
                timer: 2000
            })
        },
        error: (result) => {
            console.log(result)
            Swal.fire({
                icon: 'error',
                title: 'Oops... Something went wrong!',
                text: 'Please check the input fields, make sure nothing is empty!'
              })
        }
    })

}

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "api/country/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#country-up-id').val(`${result.id}`)
            $('#country-up-code').val(`${result.code}`)
            $('#country-up-name').val(`${result.name}`)
            $('.country-in-region').val(`${result.region.id}`)
            // $('#country-up-region').append(
            //     '<option value="'
            //                  + result.region.id
            //                  + '" selected>'
            //                  + result.region.name
            //                  + '</option>'
            // )
        }
    })
}

function update() {
    let valId = $('#country-up-id').val()
    let valCode = $('#country-up-code').val()
    let valName = $('#country-up-name').val()
    let valRegion = $('.country-up-region').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this country!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/country/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    code: valCode,
                    name: valName,
                    region: {
                        id: valRegion
                    }
                }),
                success: result => {
                    $('#update-country').modal('hide')
                    $('#country-table').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully to update',
                        showConfirmButton: false,
                        timer: 2000
                    })
                },
                error: (result) => {
                    console.log(result)
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops... Something went wrong!',
                        text: 'Please check the input fields, make sure nothing is empty!'
                      })
                }
            })
        }
    })
}

function countryDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this country!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/country/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#country-table').DataTable().ajax.reload()
                    Swal.fire({
                        title: 'Successfully to Delete',
                        width: 600,
                        padding: '3em',
                        color: '#716add',
                        background: '#fff',
                        backdrop: `
                            rgba(0,0,123,0.4)
                            url("https://ask.libreoffice.org/uploads/asklibo/original/3X/3/5/35664d063435f940bda4cb3bb31ea0a6c5fed2f4.gif")
                            left top
                            no-repeat
                        `,
                        icon: 'success',
                        showConfirmButton: false,
                        timer: 5000
                    })
                }
            })
        }
    })

}
