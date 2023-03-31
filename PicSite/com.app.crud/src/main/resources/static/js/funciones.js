/*
function enviarOpcionSeleccionada(event) {
  event.preventDefault();
  var opcionSeleccionada = event.target.getAttribute("data-opcion");
  var url = "/g?opcion=" + opcionSeleccionada;

  var xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xhr.send();
  }
}*/

function enviarOpcionSeleccionada(event) {
  event.preventDefault();
  var opcionSeleccionada = event.target.getAttribute("data-opcion");
  var url = "/g?opcion=" + opcionSeleccionada;

  var xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      window.location.href = "/galeriaredirect";
    }
  };

  xhr.send();
}

/*


function enviarOpcionSeleccionada(event) {
  event.preventDefault();
  var opcionSeleccionada = event.target.getAttribute("data-opcion");
  var url = "/g?opcion=" + opcionSeleccionada;

  $.ajax({
    url: url,
    type: "GET",
    dataType: "json",
    success: function(data) {
      console.log(data);
      window.location.href = "/galeria";
    },
    error: function(xhr, status, error) {
      console.log(error);
    }
  });
}
*/