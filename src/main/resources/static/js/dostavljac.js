$(document).on("submit","#loginDostavljac",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();

    var noviKorisnik = formToJson2(korisnickoIme,lozinka);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/dostavljac/login",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(korisnickoIme + " se uspesno ulogovao/la!");
                window.location.href = "dostavljacStrana.html";
            },
            error:function (data)
            {
                alert("Greska prilikom logovanja!");
            }
        }
    );
});

function formToJson2(ki,loz)
{
    return JSON.stringify(
        {
            "korisnickoIme":ki,
            "password":loz
        }
    );
}

$(document).on("click","#prikazR",function (){
    var Table = document.getElementById("sviRestorani");
    Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/admin/restorani",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + data[i]['id'] + "</td>";
                row+="<td>" + data[i]['kupac'] + "</td>";
                row+="<td>" + data[i]['poruceniArtikli'] + "</td>";
                row+="<td>" + data[i]['restoranPoruceno'] + "</td>";
                row+="<td>" + data[i]['datumIVremePorudzbine'] + "</td>";
                row+="<td>" + data[i]['status'] + "</td>";
                row+="<td>" + data[i]['ukupnaCena'] + "</td>";

                $('#sviRestorani').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});

$(document).on("click","#logout",function (/*event*/){
    //event.preventDefault();

    // var naziv = $("#nazivR").val();
    // var tip = $("#tipR").val();
    //
    // var noviRestoran = formToJson3(naziv,tip);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/menadzer/logout",
            //dataType:"json",
            //contentType:"application/json",
            success:function()
            {
                alert("Menadzer se uspesno izlogovao!");
                window.location.href = "menadzerLogin.html";
            },
            error:function ()
            {
                alert("Greska prilikom logouta!");
            }
        }
    );
});