$(document).ready(function () {
    // $('#user-table').DataTable({
    //     ajax: {
    //         url: 'api/user',
    //         dataSrc: ''
    //     },
    //     columns: [{
    //             data: 'id'
    //         },
    //         {
    //             data: 'username'
    //         },
    //         {
    //             data: function ( data, row, type, set ) {
    //                 if ( data.roles[1] ) {
    //                     return data.roles[0].name+', '+data.roles[1].name;
    //                 } if ( data.roles[0]) {
    //                     return data.roles[0].name+`
    //                     <button type="button" class="btn btn-danger"
    //                         onclick="removeRole(${data.roles[0].id},${data.id})">
    //                         REMOVE ROLE
    //                     </button>
    //                     `
    //                 } else {
    //                     return null;
    //                 }
    //             }
    //         },
    //         {
    //             data: function ( data, row, type, set ) {
    //                 if ( data.roles[1] ) {
    //                     return data.roles[0].privileges[0].name+', '+data.roles[0].privileges[1].name+', '+data.roles[0].privileges[2].name+', '+data.roles[0].privileges[3].name+', '+data.roles[1].privileges[0].name+', '+data.roles[1].privileges[1].name+', '+data.roles[1].privileges[2].name+', '+data.roles[1].privileges[3].name
    //                 } if ( data.roles[0] ) {
    //                     return data.roles[0].privileges[0].name+', '+data.roles[0].privileges[1].name+', '+data.roles[0].privileges[2].name+', '+data.roles[0].privileges[3].name
    //                 } else {
    //                     return null;
    //                 }
    //             }
    //         },
    //         {
    //             "data": null,
    //             render: function (data, row, type, meta) {
    //                 return `
    //                 <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addrole-user"
    //                     onclick="beforeAddRole(${data.id})">
    //                     ADD ROLE
    //                 </button>
    //                 `;
    //             }
    //         },
    //         {
    //             "data": null,
    //             render: function (data, row, type, meta) {
    //                 return `
    //                 <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#detail-user"
    //                     onclick="detail(${data.id})">
    //                     <i class="bi bi-exclamation-circle-fill"></i>
    //                 </button>

    //                 <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#update-user"
    //                     onclick="beforeUpdate(${data.id})">
    //                     <i class="bi bi-pencil-square"></i>
    //                 </button>

    //                 <button type="button" class="btn btn-danger"
    //                     onclick="userDelete(${data.id})">
    //                     <i class="bi bi-trash3-fill"></i>
    //                 </button>
                    
    //                 `;
    //             }
    //         }
    //     ]
    // });

    $.ajax({
        method: "GET",
        url: "api/role",
        dataType: "JSON",
        success: function (result) {
            $.each(result, function(key, value) {
                $('.add-in-role').append(
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

// function detail(id) {
//     $.ajax({
//         method: "GET",
//         url: "api/user/" + id,
//         dataType: "JSON",
//         success: function (result) {
//             $('#user-det-id').val(`${result.id}`)
//             $('#user-det-username').val(`${result.username}`)
//             $('#user-det-password').val(`${result.password}`)
//         }
//     })
// }

// function create() {
//     let valName = $('#user-in-name').val();
//     let valEmail = $('#user-in-email').val();
//     let valPhone = $('#user-in-phone').val();
//     let valUsername = $('#user-in-username').val();
//     let valPassword = $('#user-in-password').val();
//     $.ajax({
//         method: "POST",
//         url: "api/register",
//         dataType: "JSON",
//         beforeSend: addCsrfToken(),
//         data: JSON.stringify({
//             name: valName,
//             email: valEmail,
//             phone: valPhone,
//             username: valUsername,
//             password: valPassword
//         }),
//         contentType: "application/json",
//         success: result => {
//             console.log(result)
//             $('#create-user').modal('hide')
//             $('#user-table').DataTable().ajax.reload()
//             Swal.fire({
//                 position: 'center',
//                 icon: 'success',
//                 title: 'Successfully to insert',
//                 showConfirmButton: false,
//                 timer: 2000
//             })
//         },
//         error: (result) => {
//             console.log(result)
//             Swal.fire({
//                 icon: 'error',
//                 title: 'Oops... Something went wrong!',
//                 text: 'Please check the input fields, make sure nothing is empty!'
//               })
//         }
//     })
// }

// function beforeUpdate(id) {
//     $.ajax({
//         method: "GET",
//         url: "api/user/" + id,
//         dataType: "JSON",
//         success: function (result) {
//             $('#user-up-username').val(`${result.username}`)
//             $('#user-up-id').val(`${result.id}`)
//         }
//     })
// }

// function update() {
//     let valId = $('#user-up-id').val()
//     let valUsername = $('#user-up-username').val()
//     let valPassword = $('#user-up-password').val()
//     Swal.fire({
//         title: 'Are you sure?',
//         text: "Do you want to change this user!",
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         confirmButtonText: 'Yes'
//     }).then((result) => {
//         if (result.isConfirmed) {
//             $.ajax({
//                 method: "PUT",
//                 url: "api/user/" + valId,
//                 dataType: "JSON",
//                 beforeSend: addCsrfToken(),
//                 contentType: "application/json",
//                 data: JSON.stringify({
//                     username: valUsername,
//                     password: valPassword
//                 }),
//                 success: result => {
//                     $('#update-user').modal('hide')
//                     $('#user-table').DataTable().ajax.reload()
//                     Swal.fire({
//                         position: 'center',
//                         icon: 'success',
//                         title: 'Successfully to update',
//                         showConfirmButton: false,
//                         timer: 2000
//                     })
//                 },
//                 error: (result) => {
//                     console.log(result)
//                     Swal.fire({
//                         icon: 'error',
//                         title: 'Oops... Something went wrong!',
//                         text: 'Please check the input fields, make sure nothing is empty!'
//                       })
//                 }
//             })
//         }
//     })
// }

// function userDelete(id) {

//     Swal.fire({
//         title: 'Are you sure?',
//         text: "You won't be delete this user!",
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         confirmButtonText: 'Yes, delete it!'
//     }).then((result) => {
//         if (result.isConfirmed) {
//             $.ajax({
//                 method: "DELETE",
//                 url: "api/user/" + id,
//                 dataType: "JSON",
//                 beforeSend: addCsrfToken(),
//                 success: result => {
//                     $('#user-table').DataTable().ajax.reload()
//                     Swal.fire({
//                         title: 'Successfully to Delete',
//                         width: 600,
//                         padding: '3em',
//                         color: '#716add',
//                         background: '#fff',
//                         backdrop: `
//                             rgba(0,0,123,0.4)
//                             url("https://ask.libreoffice.org/uploads/asklibo/original/3X/3/5/35664d063435f940bda4cb3bb31ea0a6c5fed2f4.gif")
//                             left top
//                             no-repeat
//                         `,
//                         icon: 'success',
//                         showConfirmButton: false,
//                         timer: 5000
//                     })
//                 }
//             })
//         }
//     })

// }

function beforeAddRole(id) {
    $.ajax({
        method: "GET",
        url: "api/user/" + id,
        dataType: "JSON",
        success: function (result) {
            $('#add-in-id').val(`${result.id}`)
        }
    })
}

function addRole() {
    let valId = $('#add-in-id').val();
    let valRole = $('.add-in-role').val();
    $.ajax({
        method: "POST",
        url: "api/user/add/" + valId,
        dataType: "JSON",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            id: valRole,
        }),
        contentType: "application/json",
        success: result => {
            console.log(result)
            $('#add-role').modal('hide')
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