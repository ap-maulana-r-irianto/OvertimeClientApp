$(document).ready(function () {
    $('#reimburse-table').DataTable({
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
            data: 'date_time'
        },
        {
            data: 'type.name'
        },
        {
            data: 'file_url'
        },
        {
            data: 'status.name'
        },
        {
            data: 'employee.name'
        }

        ]
    });

    $.ajax({
        method: "GET",
        url: "api/type",
        dataType: "JSON",
        success: function (result) {
            // $('#overtime-in-employee').append(
            //     '<option value="">Choose Your employee</option>'
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

    $.ajax({
        method: "GET",
        url: "api/status",
        dataType: "JSON",
        success: function (result) {
            // $('#overtime-in-employee').append(
            //     '<option value="">Choose Your employee</option>'
            // );
            $.each(result, function (key, value) {
                $('.reimburse-up-status').append(
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
    let valNominal = $('#reimburse-in-nominal').val()
    let valDescription = $('#reimburse-in-description').val()
    let valDateTime = $('#reimburse-in-date_time').val()
    let valFile = $('#reimburse-in-file').val()
    let valType = $('#reimburse-in-type').val()
    let valEmployee = $('#reimburse-in-employee').val()
    $.ajax({
        method: "POST",
        url: "api/reimburse",
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            nominal: valNominal,
            description: valDescription,
            date_time: valDateTime,
            file_url: valFile,
            type_id: valType,
            employee: {
                id: valEmployee
            }
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
        url: "api/reimburse/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#reimburse-up-id').val(`${result.id}`)
            $('#reimburse-up-nominal').val(`${result.nominal}`)
            $('#reimburse-up-description').val(`${result.description}`)
            $('#reimburse-up-date_time').val(`${result.date_time}`)
            $('#reimburse-up-file').val(`${result.file_url}`)
            $('#reimburse-up-type').val(`${result.type.id}`)
            $('.reimburse-up-status').val(`${result.status.id}`)
            $('#reimburse-up-employee').val(`${result.employee.id}`)
        }
    })
}

function update() {
    let valId = $('#reimburse-up-id').val()
    let valNominal = $('#reimburse-up-nominal').val()
    let valDescription = $('#reimburse-up-description').val()
    let valDateTime = $('#reimburse-up-date_time').val()
    let valFile = $('#reimburse-up-file').val()
    let valType = $('#reimburse-up-type').val()
    let valStatus = $('.reimburse-up-status').val()
    let valEmployee = $('#reimburse-up-employee').val()

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
                url: "api/reimburse/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    nominal: valNominal,
                    description: valDescription,
                    date_time: valDateTime,
                    file_url: valFile,
                    type_id: valType,
                    status: {
                        id: valStatus
                    },
                    employee: {
                        id: valEmployee
                    }
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