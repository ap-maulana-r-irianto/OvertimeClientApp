$(document).ready(function () {
    $('#type-table').DataTable({
        ajax: {
            url: 'api/type',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'name'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-type"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-type"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="typeDelete(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    
                    `;
                }
            }
        ]
    });
});

function detail(id) {
    $.ajax({
        method: "GET",
        url: "api/type/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#type-det-id').val(`${result.id}`)
            $('#type-det-name').val(`${result.name}`)
        }
    })
}

function create() {
    let valName = $('#type-in-name').val();
    $.ajax({
        method: "POST",
        url: "api/type",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: valName
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-type').modal('hide')
            $('#type-table').DataTable().ajax.reload()
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
        url: "api/type/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#type-up-name').val(`${result.name}`)
            $('#type-up-id').val(`${result.id}`)
        }
    })
}

function update() {
    let valId = $('#type-up-id').val()
    let valName = $('#type-up-name').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this type!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/type/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    name: valName
                }),
                success: result => {
                    $('#update-type').modal('hide')
                    $('#type-table').DataTable().ajax.reload()
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

function typeDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this type!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/type/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#type-table').DataTable().ajax.reload()
                    Swal.fire({
                        title: 'Successfully to Delete',
                        width: 600,
                        padding: '3em',
                        color: '#716add',
                        background: '#fff',
                        backdrop: `
                            rgba(0,0,123,0.4)
                            url("https://media.tenor.com/3ksij76-6M4AAAAC/sarahs-scribbles-throw.gif")
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