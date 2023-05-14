$(document).ready(function () {
    $('#reimburese-table').DataTable({
        ajax: {
            url: 'api/reimburse',
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
            data: 'file'
        },
        {
            data: 'status'
        },
        {
            data: 'employee.name'
        },
        {
            data: 'type.name'
        },
        {
            "data": null,
            render: function (data, row, type, meta) {
                return `
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-reimburse"
                        onclick="detail(${data.id})">
                        <i class="bi bi-exclamation-circle-fill"></i>
                    </button>

                    <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-reimburse"
                        onclick="beforeUpdate(${data.id})">
                        <i class="bi bi-pencil-square"></i>
                    </button>

                    <button type="button" class="btn btn-danger"
                        onclick="reimburseDelete(${data.id})">
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
            // $('#reimburse-in-employee').append(
            //     '<option value="">Choose Your employee</option>'
            // );
            $.each(result, function (key, value) {
                $('.reimburse-in-employee').append(
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
        url: "api/type",
        dataType: "JSON",
        success: function (result) {
            // $('#reimburse-in-type').append(
            //     '<option value="">Choose Your type</option>'
            // );
            $.each(result, function (key, value) {
                $('.reimburse-in-type').append(
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
    let valNominal = $('#reimburse-in-nominal').val();
    let valDescription = $('#reimburse-in-description').val();
    let valFile = $('#reimburse-in-file').val();
    let valStatus = $('#reimburse-in-status').val();
    let valEmployee = $('.reimburse-in-employee').val();
    let valType = $('.reimburse-in-type').val();
    $.ajax({
        method: "POST",
        url: "api/reimburse",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            nominal: valNominal,
            description: valDescription,
            file: valFile,
            status: valStatus,
            employee: valEmployee,
            type: valType
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#create-reimburse').modal('hide')
            $('#reimburse-table').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Pengajuan Reimburse Berhasil',
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
        url: "api/reimburse/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#reimburse-up-id').val(`${result.id}`)
            $('#reimburse-up-nominal').val(`${result.nominal}`)
            $('#reimburse-up-description').val(`${result.description}`)
            $('#reimburse-up-file').val(`${result.file}`)
            $('#reimburse-up-status').val(`${result.status}`)
            $('.reimburse-in-employee').val(`${result.employee.id}`)
            $('.reimburse-in-type').val(`${result.type.id}`)

        }
    })
}

function update() {
    let valId = $('#reimburse-up-id').val()
    let valNominal = $('#reimburse-up-nominal').val()
    let valDescription = $('#reimburse-up-description').val()
    let valFile = $('#reimburse-up-file').val()
    let valStatus = $('#reimburse-up-status').val()
    let valEmployee = $('.reimburse-in-employee').val()
    let valType = $('.reimburse-in-type').val()

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
                url: "api/reimburse/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    nominal: valNominal,
                    description: valDescription,
                    file: valFile,
                    status: valStatus,
                    employee: valEmployee,
                    type: valType
                }),
                success: result => {
                    $('#update-reimburse').modal('hide')
                    $('#reimburse-table').DataTable().ajax.reload()
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

function reimburseDelete(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this reimburse!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "api/reimburse/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#reimburse-table').DataTable().ajax.reload()
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
        url: "api/reimburse/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#reimburse-det-id').val(`${result.id}`)
            $('#reimburse-det-nominal').val(`${result.nominal}`)
            $('#reimburse-det-description').val(`${result.description}`)
            $('#reimburse-det-file').val(`${result.file}`)
            $('#reimburse-det-status').val(`${result.status}`)
            $('#reimburse-det-employee').val(`${result.employee.name}`)
            $('#reimburse-det-type').val(`${result.type.name}`)
        }
    })
}
