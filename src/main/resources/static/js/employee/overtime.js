$(document).ready(function () {
    $('#overtime-table').DataTable({
        ajax: {
            url: 'api/overtime',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'start_time'
            },
            {
                data: 'end_time'
            },
            {
                data: 'description'
            },
            {
                data: 'nominal'
            },
            {
                data: 'name'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-overtime"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-overtime"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="overtimeDelete(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    
                    `;
                }
            }

        ]
    });

  
});


function create() {
    let valStart_time = $('#overtime-in-start_time').val();
    let valEnd_time = $('#overtime-in-end_time').val();
    let valDescription = $('#overtime-in-description').val();
    $.ajax({
        method: "POST",
        url: "api/overtime",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            start_time: valStart_time,
            end_time: valEnd_time,
            description: valDescription
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-overtime').modal('hide')
            $('#overtime-table').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Pengajuan Overtime Berhasil',
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
        url: "api/overtim e/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#overtime-up-id').val(`${result.id}`)
            $('#overtime-up-start_time').val(`${result.start_time}`)
            $('#overtime-up-end_time').val(`${result.end_time}`)
            $('#overtime-up-description').val(`${result.description}`)
        }
    })
}

function update() {
    let valId = $('#overtime-up-id').val()
    let valStart_time = $('#overtime-up-start_time').val()
    let valEnd_time = $('#overtime-up-end_time').val()
    let valDescription = $('#overtime-up-description').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this submission!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/overtime/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    start_time: valStart_time,
                    end_time: valEnd_time,
                    description: valDescription
                }),
                success: result => {
                    $('#update-overtime').modal('hide')
                    $('#overtime-table').DataTable().ajax.reload()
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

function overtimeDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this overtime!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/overtime/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#overtime-table').DataTable().ajax.reload()
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

function detail(id) {
    $.ajax({
        method: "GET",
        url: "api/overtime/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#overtime-det-id').val(`${result.id}`)
            $('#overtime-det-start_time').val(`${result.start_time}`)
            $('#overtime-det-end_time').val(`${result.end_time}`)
            $('#overtime-det-status').val(`${result.status}`)
            $('#overtime-det-description').val(`${result.description}`)
        }
    })
}