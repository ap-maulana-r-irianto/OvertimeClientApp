$(document).ready(function () {
    $('#employee-table').DataTable({
        ajax: {
            url: 'api/employee',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'name'
            },
            {
                data: 'email'
            },
            {
                data: 'phone'
            },
            //  
            {
                data: 'department.name'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-employee"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-employee"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="employeeDelete(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    
                    `;
                }
            }
        ]
    });

    $.ajax({
        method: "GET",
        url: "api/department",
        dataType: "JSON",
        success: function (result) {
            $.each(result, function(key, value) {
                $('.employee-in-department').append(
                    '<option value="'
                                 + value.id
                                 + '">'
                                 + value.name
                                 + '</option>'
                ),
                $('.employee-up-department').append(
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
        url: "api/employee/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#employee-det-id').val(`${result.id}`)
            $('#employee-det-name').val(`${result.name}`)
            $('#employee-det-email').val(`${result.email}`)
            $('#employee-det-phone').val(`${result.phone}`)
            $('#employee-det-payroll').val(`${result.payroll}`)
            $('#employee-det-department').val(`${result.department.name}`)
        }
    })
}

function create() {
    let valName = $('#employee-in-name').val();
    let valEmail = $('#employee-in-email').val();
    let valPhone = $('#employee-in-phone').val();
    let valPayroll = $('#employee-in-payroll').val();
    let valDepartment = $('.employee-in-department').val();
    // let valUsername = $('#employee-in-username').val();
    // let valPassword = $('#employee-in-password').val();
    $.ajax({
        method: "POST",
        url: "api/register",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: valName,
            email: valEmail,
            phone: valPhone,
            payroll: valPayroll,
            department: {
                id: valDepartment
            }
            // username: valUsername,
            // password: valPassword
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-employee').modal('hide')
            $('#employee-table').DataTable().ajax.reload()
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
        url: "api/employee/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#employee-up-name').val(`${result.name}`)
            $('#employee-up-email').val(`${result.email}`)
            $('#employee-up-phone').val(`${result.phone}`)
            $('#employee-up-payroll').val(`${result.payroll}`)
            $('.employee-in-department').val(`${result.department.id}`)
            // $('#employee-up-username').val(`${result.username}`)
            // $('#employee-up-password').val(`${result.password}`)
            $('#employee-up-id').val(`${result.id}`)
        }
    })
}

function update() {
    let valId = $('#employee-up-id').val()
    let valName = $('#employee-up-name').val()
    let valEmail = $('#employee-up-email').val()
    let valPhone = $('#employee-up-phone').val()
    let valPayroll = $('#employee-up-payroll').val()
    let valDepartment = $('.employee-up-department').val()
    // let valUsername = $('#employee-up-username').val()
    // let valPassword = $('#employee-up-password').val()
    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this employee!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/employee/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    name: valName,
                    email: valEmail,
                    phone: valPhone,
                    payroll: valPayroll,
                    department: {
                        id: valDepartment
                    }
                    // username: valUsername,
                    // password: valPassword

                }),
                success: result => {
                    $('#update-employee').modal('hide')
                    $('#employee-table').DataTable().ajax.reload()
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

function employeeDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this employee!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/employee/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#employee-table').DataTable().ajax.reload()
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