$(document).ready(function () {
    $('#employeeproject-table').DataTable({
        ajax: {
            url: 'api/employeeproject',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'employee.name'
            },
            {
                data: 'project.name'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-danger"
                        onclick="employeeprojectDelete(${data.id})">
                        <i class="bi bi-trash3-fill"></i>
                    </button>
                    `;
                }
            }

        ]
    });

    $.ajax({
        method: "GET",
        url: "api/employee",
        dataType: "JSON",
        success: function (result) {
            // $('#employeeproject-in-employee').append(
            //     '<option value="">Choose Your employee</option>'
            // );
            $.each(result, function (key, value) {
                $('.employeeproject-in-employee').append(
                    '<option value="'
                    + value.id
                    + '">'
                    + value.name
                    + '</option>'
                )
            })
        }
    })

    $.ajax({
        method: "GET",
        url: "api/project",
        dataType: "JSON",
        success: function (result) {
            // $('#employeeproject-in-project').append(
            //     '<option value="">Choose Your project</option>'
            // );
            $.each(result, function (key, value) {
                $('.employeeproject-in-project').append(
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
    let valName = $('#employeeproject-in-name').val();
    let valEmployee = $('.employeeproject-in-employee').val();
    let valProject = $('.employeeproject-in-project').val();
    $.ajax({
        method: "POST",
        url: "api/employeeproject",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            employee: {
                id: valEmployee
            },
            project: {
                id: valProject
            }
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-employeeproject').modal('hide')
            $('#employeeproject-table').DataTable().ajax.reload()
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

function employeeprojectDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this employeeproject!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/employeeproject/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#employeeproject-table').DataTable().ajax.reload()
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
