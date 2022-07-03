$(document).on("submit","#register",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();
    var pol = $("#pol").val();
    var datumRodjenja = $("#datumRodjenja").val();

    var noviKorisnik = formToJson1(korisnickoIme,lozinka,ime,prezime,pol,datumRodjenja);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/registracija",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(korisnickoIme + " se uspesno registrovao/la!");
                window.location.href="loginKupac.html";
            },
            error:function (data)
            {
                alert("Greska prilikom registracije!");
            }
        }
    );
});

function formToJson1(ki,loz,im,prez,pol,dR)
{
    return JSON.stringify(
        {
            "korisnickoIme":ki,
            "lozinka":loz,
            "ime":im,
            "prezime":prez,
            "pol":pol,
            "datumRodjenja":dR
        }
    );
}

$(document).on("submit","#loginKupac",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();

    var noviKorisnik = formToJson2(korisnickoIme,lozinka);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/login",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(korisnickoIme + " se uspesno ulogovao/la!");
                window.location.href="homePage.html";
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

$(document).on("click","#logout",function ()
{
    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac/logout",
            success:function()
            {
                alert("Korisnik se uspesno izlogovao!");
                window.location.href = "loginKupac.html";
            },
            error:function ()
            {
                alert("Greska prilikom logouta!");
            }
        }
    );
});

$(document).on("click","#prikaz",function (){

    var Table = document.getElementById("svePorudzbine");
    Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/pregled-porudzbina",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + data[i]['id'] + "</td>";
                row+="<td>" + data[i]['dostavljac'] + "</td>";
                row+="<td>" + data[i]['restoranPoruceno'] + "</td>";
                row+="<td>" + data[i]['datumIVremePorudzbine'] + "</td>";
                row+="<td>" + data[i]['status'] + "</td>";
                row+="<td>" + data[i]['ukupnaCena'] + "</td>";

                $('#svePorudzbine').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});

$(document).ready(function (){

    //var Table = document.getElementById("svePorudzbine");
    //Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/restorani",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">" + data[i].naziv + "</h5>\n" +
                    "    <h6 class=\"card-subtitle mb-2 text-muted\">" + data[i].tip + "</h6>\n" +
                    "    <p class=\"card-text\">" + data[i].lokacija + "</p>\n" +
                    "    <a href=\"#\" class=\"card-link\">Card link</a>\n" +
                    "    <a href=\"#\" class=\"card-link\">Another link</a>\n" +
                    "  </div>\n" +
                    "</div>";
                $('#restorani').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});


$(document).ready(function (){

    //var Table = document.getElementById("svePorudzbine");
    //Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac-pregled",
        dataType:"json",
        success:function (data) {
            console.log(data);
            $('#korIme').val(data['korisnickoIme']);
            $('#ime').val(data['ime']);
            $('#prezime').val(data['prezime']);
            $('#pol').val(data['pol']);
            $('#uloga').val(data['uloga']);
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});

$(document).on("submit","#podaci",function (event){
    event.preventDefault();

    var korisnickoIme = $("#ime").val();
    var lozinka = $("#prezime").val();
    var p = $("#pol").val();
    var datum;

    var noviKorisnik = formToJson4(korisnickoIme,lozinka,p,datum);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/kupac-izmeni",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert("Uspesno izmenjeni podaci");
            },
            error:function (data)
            {
                alert("Greska prilikom promene podataka!",data);
            }
        }
    );
});

function formToJson4(ki,loz,p,dr)
{
    return JSON.stringify(
        {
            "ime":ki,
            "prezime":loz,
            "pol":p,
            "datumRodjenja":dr
        }
    );
}




