$(document).on("submit","#register",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();
    var ime = $("#ime").val();
    var prezime = $("#prezime").val();
    var pol = $("#pol").val();
    var datumRodjenja = $("#datumRodjenja").val();

    var noviKorisnik = formToJson1(korisnickoIme,lozinka,ime,prezime,pol,datumRodjenja);
    console.log(noviKorisnik);

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


$(document).on("click",".btn",function (){
    var Table = document.getElementById("sviArtikli");
    Table.innerHTML = "";
    var k = this.id;
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/" + this.id + "/artikli",
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
                row+="<td>" + "<input type=\"text\">" + "</td>";
                row+="<td>" + "<button class=\"btnSelect\">Dodaj u korpu</button>" + "</td>";

                $('#sviArtikli').append(row);
            }
        },
        error:function (data){
            //alert(data['naziv'] + data['cena']);
            console.log("GRESKA:",data)
        }
    });
});

///api/kupac/{id}/dodaj-u-korpu
// $(document).on("#klik",".bojler",function (event){
//     event.preventDefault();
//
//     var nazivArtikla = $("#artikal").val();
//     var restoran = $("#restoran").val();
//     var kolicina = $("#kolicina").val();
//
//     var novaKorpa = formToJson1(nazivArtikla,kolicina,restoran);
//
//     $.ajax(
//         {
//             type:"POST",
//             url:"http://localhost:8080/api/kupac/" + restoran + "/dodaj-u-korpu",
//             dataType:"json",
//             contentType:"application/json",
//             data:novaKorpa,
//             success:function()
//             {
//                 alert(nazivArtikla + " je uspesno dodato u korpu!");
//             },
//             error:function (data)
//             {
//                 alert("Greska prilikom dodavanja u korpu!");
//             }
//         }
//     );
// });



$(document).ready(function () {
        $("#tabela").on("click", ".btnSelect", function (){
            var currentRow = $(this).closest("tr");

            var IDartikal = currentRow.find("td:eq(1)").text();
            var kolicina = currentRow.find("td:eq(6) input[type='text']").val();
            var nazivRestorana = currentRow.find("td:eq(0)").text();

            var novaKorpa = formToJson10(IDartikal, kolicina, nazivRestorana);

            console.log(novaKorpa);
            $.ajax(
                {
                    type:"POST",
                    url:"http://localhost:8080/api/kupac/dodaj-u-korpu",
                    dataType:"json",
                    contentType:"application/json",
                    data:novaKorpa,
                    success: function () {
                        alert("Uspesno dodato u korpu!");
                    },
                    error: function (data) {
                        console.log(data);
                        alert("Greska prilikom dodavanja u korpu!" + data);
                        console.log(data);
                    }
                }
            );

        });
    }
);

function formToJson10(art,kol,naz) {
    return JSON.stringify({
        "idartikal":art,
        "kolicina":kol,
        "nazivRestorana":naz
    });
}

$(document).ready(function (){
    var Table = document.getElementById("korpaArtikli");
    Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/kupac/pregled-korpe/artikli",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + data[i]['naziv'] + "</td>";
                row+="<td>" + data[i]['cena'] + "</td>";
                row+="<td>" + data[i]['tip'] + "</td>";
                row+="<td><button type='button' id='smanji'>-</button></td>";
                row+="<td>" + data[i]['kolicina'] + "</td>";
                row+="<td><button type='button' id='povecaj'>+</button></td>";
                row+="<td>" + data[i]['opis'] + "</td>";
                row+="<td><button type='button' class='nesto' id='"+data[i]['naziv']+"'>x</button></td>";


                if(i==data.length-1)
                {
                    row+="<td><button type='button' class='nesto1' id='"+data[i]['nazivRestorana']+"'>Poruci</button></td>";
                }

                $('#korpaArtikli').append(row);
            }
        },
        error:function (data){
            //alert(data['naziv'] + data['cena']);
            console.log("GRESKA:",data)
        }
    });
});

$(document).ready(function () {
        $("#korpaArtikli").on("click", "#smanji", function (){
            var currentRow = $(this).closest("tr");

            var IDartikal = currentRow.find("td:eq(0)").text();
            var novaKorpa = formToJson15(IDartikal,1);

            console.log(novaKorpa);
            $.ajax(
                {
                    type:"POST",
                    url:"http://localhost:8080/api/kupac/pregled-korpe/smanji-kolicinu",
                    dataType:"json",
                    contentType:"application/json",
                    data:novaKorpa,
                    success: function () {
                        location.reload();
                    },
                    error: function (data) {
                        console.log(data);
                        alert("Greska prilikom smanjivanja!");
                        console.log(data);
                    }
                }
            );

        });
    }
);

function formToJson15(art,kol) {
    return JSON.stringify({
        "nazivArtikla":art,
        "kolicina":kol
    });
}

$(document).ready(function () {
        $("#korpaArtikli").on("click", "#povecaj", function (){
            var currentRow = $(this).closest("tr");

            var IDartikal = currentRow.find("td:eq(0)").text();
            var novaKorpa = formToJson15(IDartikal,1);

            console.log(novaKorpa);
            $.ajax(
                {
                    type:"POST",
                    url:"http://localhost:8080/api/kupac/pregled-korpe/povecaj-kolicinu",
                    dataType:"json",
                    contentType:"application/json",
                    data:novaKorpa,
                    success: function () {
                        location.reload();
                    },
                    error: function (data) {
                        console.log(data);
                        alert("Greska prilikom povecavanja!");
                        console.log(data);
                    }
                }
            );

        });
    }
);

$(document).ready(function () {
        $("#korpaArtikli").on("click", ".nesto", function (){
            //var currentRow = $(this).closest("tr");

            //var IDartikal = currentRow.find("td:eq(0)").text();

            $.ajax(
                {
                    type:"POST",
                    url:"http://localhost:8080/api/kupac/restoran/izbaci-iz-korpe/" + this.id,
                    dataType:"json",
                    contentType:"application/json",
                    success: function () {
                        location.reload();
                    },
                    error: function () {
                        alert("Greska prilikom izbacivanja!");
                    }
                }
            );

        });
    }
);

$(document).ready(function () {
        $("#korpaArtikli").on("click", ".nesto", function (){
            //var currentRow = $(this).closest("tr");

            //var IDartikal = currentRow.find("td:eq(0)").text();

            $.ajax(
                {
                    type:"POST",
                    url:"http://localhost:8080/api/kupac/restoran/izbaci-iz-korpe/" + this.id,
                    dataType:"json",
                    contentType:"application/json",
                    success: function () {
                        location.reload();
                    },
                    error: function () {
                        alert("Greska prilikom izbacivanja!");
                    }
                }
            );

        });
    }
);
