$(document).ready(function () {
    $('#role-table').DataTable({
        ajax: {
            url: 'api/role',
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
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-role"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-role"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="roleDelete(${data.id})">
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
        url: "api/role/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#role-det-id').val(`${result.id}`)
            $('#role-det-name').val(`${result.name}`)
        }
    })
}

function create() {
    let valName = $('#role-in-name').val();
    $.ajax({
        method: "POST",
        url: "api/role",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: valName
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-role').modal('hide')
            $('#role-table').DataTable().ajax.reload()
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
        url: "api/role/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#role-up-name').val(`${result.name}`)
            $('#role-up-id').val(`${result.id}`)
        }
    })
}

function update() {
    let valId = $('#role-up-id').val()
    let valName = $('#role-up-name').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this role!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/role/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    name: valName
                }),
                success: result => {
                    $('#update-role').modal('hide')
                    $('#role-table').DataTable().ajax.reload()
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

function roleDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this role!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/role/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#role-table').DataTable().ajax.reload()
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