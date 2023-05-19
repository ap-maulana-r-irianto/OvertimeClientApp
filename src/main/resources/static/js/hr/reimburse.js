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
            "data": null,
            render: function (data, row, type, meta) {
                return `
                <img src="${data.file_url}">
                    `;
            }
        },
        {
            data: 'status.name'
        },
        {
            data: 'employee.name'
        },
        {
            "data": null,
            render: function (data, row, type, meta) {
                return `
                <button type="button" class="btn btn-warning"
                    onclick="approv(${data.id})">
                    <i class="fa fa-check"></i>
                </button>

                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#reject-reimburse"
                    onclick="beforeReject(${data.id})">
                    <i class="fas fa-times-circle"></i>
                </button>

                <button type="button" class="btn btn-primary"
                    onclick="paid(${data.id})">
                    <i class="fas fa-dollar-sign"></i>
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
        url: "api/reimburse/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#reimburse-up-id').val(`${result.id}`)
        }
    })
}

function reject() {
    let valId = $('#reimburse-up-id').val()
    let valDescription = $('#reimburse-up-description').val()

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
                url: "api/reimburse/reject/hr/" + valId,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                data: JSON.stringify({
                    description: valDescription
                }),
                success: result => {
                    $('#reject-reimburse').modal('hide')
                    $('#reimburse-table').DataTable().ajax.reload()
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
                url: "api/reimburse/approv/hr/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#reimburse-table').DataTable().ajax.reload()
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

function paid(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "Do you want to paid this submission!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, paid it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/reimburse/paid/" + id,
                dataType: "JSON",
                beforeSend: addCsrfToken(),
                success: result => {
                    $('#reimburse-table').DataTable().ajax.reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Successfully to paid',
                        showConfirmButton: false,
                        timer: 2000
                    })
                }
            })
        }
    })

}