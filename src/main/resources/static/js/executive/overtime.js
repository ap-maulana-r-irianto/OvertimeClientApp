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
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-warning"
                        onclick="approv(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#reject-overtime"
                        onclick="beforeReject(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    `;
                }
            }

        ]
    });

});

function beforeReject(id) {
    $.ajax({
        method: "GET",
        url: "api/overtime/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#overtime-up-id').val(`${result.id}`)
        }
    })
}

function reject() {
    let valId = $('#overtime-up-id').val()
    let valDescription = $('#overtime-up-description').val()

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to reject this submission!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/overtime/reject/manager/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    description: valDescription
                }),
                success: result => {
                    $('#reject-overtime').modal('hide')
                    $('#overtime-table').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully to reject',
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

function approv(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to approv this submission!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, approv it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/overtime/approv/manager/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#overtime-table').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully to approv',
                        showConfirmButton: false,
                        timer: 2000
                    })
                }
            })
        }
    })

}