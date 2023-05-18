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
                data: 'nominal'
            },
            {
                data: 'description'
            },
            {
                data: 'start_time'
            },
            {
                data: 'end_time'
            },
            {
                data: 'status.name'
            },
            {
                data: 'employeeProject.employee.name'
            },
            {
                data: 'employeeProject.project.name'
            }

        ]
    });

    $.ajax({
        method: "GET",
        url: "api/status",
        dataType: "JSON",
        success: function (result) {
            // $('#overtime-in-employee').append(
            //     '<option value="">Choose Your employee</option>'
            // );
            $.each(result, function (key, value) {
                $('.overtime-up-status').append(
                    '<option value="'
                    + value.id
                    + '">'
                    + value.name
                    + '</option>'
                )
            })
        }
    })
  
});

function create() {
    let valDescription = $('#overtime-in-description').val()
    let valStart_time = $('#overtime-in-start_time').val()
    let valEnd_time = $('#overtime-in-end_time').val()
    let valEmployeeProject = 6
    $.ajax({
        method: "POST",
        url: "api/overtime",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            description: valDescription,
            start_time: valStart_time,
            end_time: valEnd_time,
            employee_project_id: valEmployeeProject
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-overtime').modal('hide')
            $('#overtime-table').DataTable().ajax.reload()
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
        url: "api/overtime/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#overtime-up-id').val(`${result.id}`)
            $('#overtime-up-nominal').val(`${result.nominal}`)
            $('#overtime-up-description').val(`${result.description}`)
            $('#overtime-up-start_time').val(`${result.start_time}`)
            $('#overtime-up-end_time').val(`${result.end_time}`)
            $('.overtime-up-status').val(`${result.status.id}`)
            $('#overtime-up-employee_project').val(`${result.employee_project.id}`)
        }
    })
}

function update() {
    let valId = $('#overtime-up-id').val()
    let valNominal = $('#overtime-up-nominal').val()
    let valDescription = $('#overtime-up-description').val()
    let valStart_time = $('#overtime-up-start_time').val()
    let valEnd_time = $('#overtime-up-end_time').val()
    let valStatus = $('.overtime-up-status').val()
    let valEmployeeProject = $('#overtime-up-employee_project').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to approval this submission!",
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
                    nominal: valNominal,
                    description: valDescription,
                    start_time: valStart_time,
                    end_time: valEnd_time,
                    status: {
                        id: valStatus
                    },
                    employeeProject: {
                        id: valEmployeeProject
                    }
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