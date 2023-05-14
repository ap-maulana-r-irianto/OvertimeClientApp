$(document).ready(function () {
    $('#project-table').DataTable({
        ajax: {
            url: 'api/project',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'description'
            },
            {
                data: 'start_date'
            },
            {
                data: 'end_date'
            },
            {
                data: 'budget'
            },
            {
                data: 'status'
            },
            {
                "data": null,
                render: function (data, row, type, meta) {
                    return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-project"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-project"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="projectDelete(${data.id})">
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
        url: "api/project/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#project-det-id').val(`${result.id}`)
            $('#project-det-description').val(`${result.description}`)
            $('#project-det-start_date').val(`${result.start_date}`)
            $('#project-det-end_date').val(`${result.end_date}`)
            $('#project-det-budget').val(`${result.budget}`)
            $('#project-det-status').val(`${result.status}`)
        }
    })
}

function create() {
    let valName = $('#project-in-name').val();
    let valDescription = $('#project-in-description').val();
    let valStart_date = $('#project-in-start_date').val();
    let valsEnd_date = $('#project-in-end_date').val();
    let valBudget = $('#project-in-budget').val();
    let valStatus = $('#project-in-status').val();
    $.ajax({
        method: "POST",
        url: "api/project",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: valName,
            description: valDescription,
            start_date: valStart_date,
            end_date:valsEnd_date,
            budget: valBudget,
            status: valStatus
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-project').modal('hide')
            $('#project-table').DataTable().ajax.reload()
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
        url: "api/project/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#project-up-name').val(`${result.name}`)
            $('#project-up-description').val(`${result.description}`)
            $('#project-up-start_date').val(`${result.start_date}`)
            $('#project-up-end_date').val(`${result.end_date}`)
            $('#project-up-budget').val(`${result.budget}`)
            $('#project-up-status').val(`${result.status}`)
            $('#project-up-id').val(`${result.id}`)
        }
    })
}

function update() {
    let valId = $('#project-up-id').val()
    let valName = $('#project-up-name').val()
    let valDescription = $('#project-up-description').val();
    let valStart_date = $('#project-up-start_date').val();
    let valsEnd_date = $('#project-up-end_date').val();
    let valBudget = $('#project-up-budget').val();
    let valStatus = $('#project-up-status').val();

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to change this project!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/project/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    name: valName,
                    description: valDescription,
                    start_date: valStart_date,
                    end_date:valsEnd_date,
                    budget: valBudget,
                    status: valStatus
                }),
                success: result => {
                    $('#update-project').modal('hide')
                    $('#project-table').DataTable().ajax.reload()
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

function projectDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this project!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/project/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#project-table').DataTable().ajax.reload()
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