$.validator.setDefaults( {

  submitHandler: function () {
    //var dataString = 'sexo='+$('.sexo').val();
    ///alert($('input:radio[name=sexo]:checked').val());
    /*var data = $('#crear-evento').serialize();
    alert('Datos serializados: '+data);
    $.ajax({
        type: "post",
        url:"Nuevo-Evento",
        data: data,
        contenType:false,
        prossesData:false,
        success: function(data){
            alert(data);
        }
    });
    */
   return true;
  }
     
  
} );

function archivo(evt) {
      var files = evt.target.files; // FileList object

        //Obtenemos la imagen del campo "file".
      for (var i = 0, f; f = files[i]; i++) {
           //Solo admitimos imágenes.
           if (!f.type.match('image.*')) {
                continue;
           }

           var reader = new FileReader();

           reader.onload = (function(theFile) {
               return function(e) {
               // Creamos la imagen.

               $( "#img-defecto" ).remove();
                      document.getElementById("list").innerHTML = ['<img  class="img-evento" style="width: 350px;height: 250px;" src="', e.target.result,'" title="', escape(theFile.name), '"/>'].join('');
               };
           })(f);

           reader.readAsDataURL(f);
       }
}

      document.getElementById('files').addEventListener('change', archivo, false);


$( document ).ready( function () {

//Funcion para el funcionamiento de los Select
  $(document).ready(function() {
     $('select').material_select();
   });

//Validar Formulario
  $( "#crear-evento" ).validate( {
    rules: {
      Titulo:{
        required: true,
        minlength: 5,
        maxlength: 30,
      },
      Publico: {
        required: true,
      },
      hora: {
        required: true,
      },
      TEvento: {
        required: true,
      },
      FechaEvento: {
        required: true,
        date: true
      },
      usuario: {
        email: true,
        required: true,
        minlength: 5,
        maxlength: 30,
      }
    },
    messages: {
      Titulo: {
        required: "Digite el Titulo",
        minlength: "minimo 5 caracteres",
        maxlength: "maximo 30 caracteres",
      },
      Publico: {
        required: "Seleccione El Publico",
      },
      hora: {
        required: "Seleccione la Hora",
      },
      TEvento: {
        required: "Seleccione El T. de Evento ",
      },
      FechaEvento: {
        required: "Seleccione la fecha",
      },
      usuario: {
        required: "Digite el Usuario",
        minlength: "minimo 10 caracteres",
        maxlength: "maximo 30 caracteres",
        email: "aaaa@aaaa.com",
      },
       Detalles: {
        required: "Digite los Detalles",
        minlength: "minimo 10 caracteres",
        maxlength: "maximo 30 caracteres",

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
