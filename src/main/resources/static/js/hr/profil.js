function beforeUpdateAccount(id) {
    $.ajax({
        method: "GET",
        url: "api/user/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#user-up-id').val(`${result.id}`)
            $('#user-up-username').val(`${result.username}`)
        }
    })
}

function beforeUpdateProfil(id) {
    $.ajax({
        method: "GET",
        url: "api/employee/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#employee-up-id').val(`${result.id}`)
            $('#employee-up-name').val(`${result.name}`)
            $('#employee-up-phone').val(`${result.phone}`)
            $('#employee-up-email').val(`${result.email}`)
            $('#employee-up-account').val(`${result.account_bank}`)
            $('#employee-up-payroll').val(`${result.payroll}`)
            $('#employee-up-department').val(`${result.department.id}`)
            $('#employee-up-manager').val(`${result.manager.id}`)
        }
    })
}

function updateAccount() {
    let valId = $('#user-up-id').val()
    let valUsername = $('#user-up-username').val()
    let valPassword = $('#user-up-password').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to update this account!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/user/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    username: valUsername,
                    password: valPassword
                }),
                success: result => {
                    $('#edit-account').modal('hide')
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

function updateProfil() {
    let valId = $('#employee-up-id').val()
    let valName = $('#employee-up-name').val()
    let valPhone = $('#employee-up-phone').val()
    let valEmail = $('#employee-up-email').val()
    let valAccount = $('#employee-up-account').val()
    let valPayroll = $('#employee-up-payroll').val()
    let valDepartment = $('#employee-up-department').val()
    let valManager = $('#employee-up-manager').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to update this profile!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/user/" + valId,
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
                    $('#edit-profil').modal('hide')
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