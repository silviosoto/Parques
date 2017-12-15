$.validator.setDefaults( {
  submitHandler: function () {
   
    /*var dataString = 'Nombre='+$('#Nombre').val()+'&Usuario='+$('#Usuario').val()+'&fecha='+$('#fecha').val()
            +'&sexo='+$('input:radio[name=sexo]:checked').val();
            +'&archivo='+$('#archivo').val();
     alert(dataString);
     */
    console.log("Hola  ");
    var data = new FormData(document.getElementById("Validar-Editar-Usuario"));
    //console.log(data);
    
  //  var data = $('#Validar-Editar-Usuario').serialize();

    ///    alert('Datos serializados: '+data);

     //alert();
     //Editar-Usuario
    
        $.ajax({
            type: "post",
            url:"Editar-Usuario",
            //data: $("form").serialize(),
            data: data,
            dataType: "html",
            //contentType: 'multipart/form-data',
            contentType: false,
	    processData: false,
            success: function(data){
                console.log("Enviado");
                alert(data);
            }
        });
  }
} );

$( document ).ready( function () {
  /*
    $('#fecha').pickadate({
      selectMonths: true, // Creates a dropdown to control month
      selectYears: 15, // Creates a dropdown of 15 years to control year,
      today: 'Hoy',
      clear: 'Limpiar',
      close: 'Ok',
      closeOnSelect: false, // Close upon selecting a date,
      format: 'yyyy/mm/dd',
      max: new Date(2002,12,31),
      min: new Date(1040,1,1)
});
*/
  $( "#Validar-Editar-Usuario" ).validate( {
    rules: {
      Nombre:{
        required: true,
        minlength: 5,
        maxlength: 30

      },
      Usuario: {
        email: true,
        required: true,
        minlength: 10,
        maxlength: 30
      },
      fecha: {
        required: true,
        date: true
      },
      sexo: {
        required: true
      }
    },
    messages: {
      Nombre: {
        required: "Digite el Nombre",
        minlength: "minimo 10 caracteres",
        maxlength: "maximo 30 caracteres"
      },
      Usuario: {
        required: "Digite el Usuario",
        minlength: "minimo 10 caracteres",
        maxlength: "maximo 30 caracteres",
        email: "aaaa@aaaa.com"
      },
      fecha: {
        required: "Seleccione la fecha"
      },
      sexo: {
        required: "Seleccione el sexo"
      }
    },
    errorElement: "em",
    errorPlacement: function ( error, element ) {
      // Add the `help-block` class to the error element
      error.addClass( "help-block" );
      error.insertAfter( element );
    },
  highlight: function ( element, errorClass, validClass ) {
      $( element ).parents( ".s6" ).addClass( ".Error" ).removeClass( "has-success" );
    },
    unhighlight: function (element, errorClass, validClass) {
      $( element ).parents( ".s6" ).addClass( "has-success" ).removeClass( ".Error" );
    }
  });
} );
