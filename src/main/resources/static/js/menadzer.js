$(document).on("submit","#loginMenadzer",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();

    var noviKorisnik = formToJson2(korisnickoIme,lozinka);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/menadzer/login",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(korisnickoIme + " se uspesno ulogovao/la!");
                window.location.href = "menadzerStrana.html";
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


$(document).on("click","#prikazip",function (){

    var Table = document.getElementById("svePorudzbine");
    Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/menadzer/porudzbine",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + data[i]['id'] + "</td>";
                row+="<td>" + data[i]['kupacIme'] + "</td>";
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


$(document).on("submit","#dodajA",function (event){
    event.preventDefault();

    var naz = $("#nazi").val();
    var t = $("#ti").val();
    var c = $("#cen").val();
    var o = $("#opi").val();
    var k = $("#kolicin").val();

    var noviArtikal = formToJson70(naz,t,c,o,k);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/menadzer/restoran/dodaj-artikal",
            dataType:"json",
            contentType:"application/json",
            data:noviArtikal,
            success:function()
            {
                alert("Artikal" + naz + " je uspesno dodat!");
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja artikla!");
            }
        }
    );
});

$(document).on("click",".btn",function (){
    var Table = document.getElementById("sviArtikli");
    Table.innerHTML = "";
    var k = this.id;
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/menadzer/" + this.id + "/artikli",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + k + "</td>"
                row+="<td>" + data[i]['naziv'] + "</td>";
                row+="<td>" + data[i]['cena'] + "</td>";
                row+="<td>" + data[i]['tip'] + "</td>";
                row+="<td>" + data[i]['kolicina'] + "</td>";
                row+="<td>" + data[i]['opis'] + "</td>";
                $('#sviArtikli').append(row);
            }
        },
        error:function (data){
            //alert(data['naziv'] + data['cena']);
            console.log("GRESKA:",data)
        }
    });
});

$(document).ready(function (){

    //var Table = document.getElementById("svePorudzbine");
    //Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/menadzer/restorani",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">" + data[i].naziv + "</h5>\n" +
                    "    <h6 class=\"card-subtitle mb-2 text-muted\">" + data[i].tip + "</h6>\n" +
                    "    <p class=\"card-text\">" + data[i].lokacija + "</p>\n" +
                    "<button id=\""+data[i].naziv+"\" type=\"button\" class=\"btn\">Proizvodi</button>\n" +
                    "</div>";
                $('#restorani').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});


function formToJson70(naz,t,c,o,k)
{
    return JSON.stringify(
        {
            "naziv":naz,
            "tip":t,
            "cena":c,
            "opis":o,
            "kolicina":k
        }
    );
}