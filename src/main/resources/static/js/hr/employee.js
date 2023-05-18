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

    $.ajax({
        method: "GET",
        url: "api/employee",
        dataType: "JSON",
        success: function (result) {
            $.each(result, function(key, value) {
                $('.employee-in-manager').append(
                    '<option value="'
                                 + value.id
                                 + '">'
                                 + value.name
                                 + '</option>'
                ),
                $('.employee-up-manager').append(
                    '<option value="'
                                 + value.id
                                 + '">'
                                 + value.name
                                 + '</option>'
                )
            })
        }
    });

    $.ajax({
        method: "GET",
        url: "api/role",
        dataType: "JSON",
        success: function (result) {
            $.each(result, function(key, value) {
                $('.employee-in-role').append(
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
            $('#employee-det-account').val(`${result.account_bank}`)
            $('#employee-det-username').val(`${result.user.username}`)
            $('#employee-det-department').val(`${result.department.name}`)
            $('#employee-det-manager').val(`${result.employee.name}`)
        }
    })
}

function create() {
    let valName = $('#employee-in-name').val();
    let valEmail = $('#employee-in-email').val();
    let valPhone = $('#employee-in-phone').val();
    let valUsername = $('#employee-in-username').val();
    let valPassword = $('#employee-in-password').val();
    let valAccount = $('#employee-in-account').val();
    let valDepartment = $('.employee-in-department').val();
    let valRole = $('.employee-in-role').val();
    let valManager = $('.employee-in-manager').val();
    $.ajax({
        method: "POST",
        url: "api/user",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: valName,
            email: valEmail,
            phone: valPhone,
            account_bank: valAccount,
            username: valUsername,
            password: valPassword,
            department_id: valDepartment,
            manager_id: valManager,
            role_id: valRole
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
            $('#employee-up-id').val(`${result.id}`)
            $('#employee-up-name').val(`${result.name}`)
            $('#employee-up-email').val(`${result.email}`)
            $('#employee-up-phone').val(`${result.phone}`)
            $('#employee-up-account').val(`${result.account_bank}`)
            $('#employee-up-payroll').val(`${result.payroll}`)
            $('.employee-up-department').val(`${result.department.id}`)
            $('.employee-up-manager').val(`${result.employee.id}`)
        }
    })
}

function update() {
    let valId = $('#employee-up-id').val()
    let valName = $('#employee-up-name').val()
    let valEmail = $('#employee-up-email').val()
    let valPhone = $('#employee-up-phone').val()
    let valAccount = $('#employee-up-account').val()
    let valPayroll = $('#employee-up-payroll').val()
    let valDepartment = $('.employee-up-department').val()
    let valManager = $('.employee-up-manager').val()
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
                    account_bank: valAccount,
                    payroll: valPayroll,
                    department: {
                        id: valDepartment
                    },
                    manager: {
                        id: valManager
                    }
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