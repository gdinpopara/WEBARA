$(document).on("submit","#loginAdmin",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme").val();
    var lozinka = $("#lozinka").val();

    var noviKorisnik = formToJson2(korisnickoIme,lozinka);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/admin/login",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert(korisnickoIme + " se uspesno ulogovao/la!");
                window.location.href = "adminStrana.html";
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


$(document).on("submit","#dodajM",function (event){
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
            url:"http://localhost:8080/api/admin/dodaj-menadzer",
            dataType:"json",
            contentType:"application/json",
            data:noviKorisnik,
            success:function()
            {
                alert("Menadzer " + korisnickoIme + " je uspesno dodat!");
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja menadzera!!");
            }
        }
    );
});

$(document).on("submit","#dodajD",function (event){
    event.preventDefault();

    var korisnickoIme = $("#korisnickoIme1").val();
    var lozinka = $("#lozinka1").val();
    var ime = $("#ime1").val();
    var prezime = $("#prezime1").val();
    var pol = $("#pol1").val();
    var datumRodjenja = $("#datumRodjenja1").val();

    var noviDostavljac = formToJson1(korisnickoIme,lozinka,ime,prezime,pol,datumRodjenja);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/admin/dodaj-dostavljac",
            dataType:"json",
            contentType:"application/json",
            data:noviDostavljac,
            success:function()
            {
                alert("Dostavljac " + korisnickoIme + " je uspesno dodat!");
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja dostavljaca!!");
            }
        }
    );
});

$(document).on("submit","#dodajR",function (event){
    event.preventDefault();

    var naziv = $("#nazivR").val();
    var tip = $("#tipR").val();

    var noviRestoran = formToJson3(naziv,tip);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/admin/dodaj-restoran",
            dataType:"json",
            contentType:"application/json",
            data:noviRestoran,
            success:function()
            {
                alert("Restoran" + naziv + " je uspesno dodat!");
            },
            error:function (data)
            {
                alert("Greska prilikom dodavanja restorana!");
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

function formToJson3(ki,loz)
{
    return JSON.stringify(
        {
            "naziv":ki,
            "tip":loz
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
            url:"http://localhost:8080/api/admin/logout",
            //dataType:"json",
            //contentType:"application/json",
            success:function()
            {
                alert("Admin se uspesno izlogovao!");
                window.location.href = "adminLogin.html";
            },
            error:function ()
            {
                alert("Greska prilikom logouta!");
            }
        }
    );
});

$(document).on("click","#prikaz",function (){

    var Table = document.getElementById("sviKorisnici");
    Table.innerHTML = "";
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/admin/pregled-korisnika",
        dataType:"json",
        success:function (data){
            for(i=0;i<data.length;i++)
            {
                var row = "<tr>";
                row+="<td>" + data[i]['korisnickoIme'] + "</td>";
                row+="<td>" + data[i]['ime'] + "</td>";
                row+="<td>" + data[i]['prezime'] + "</td>";
                row+="<td>" + data[i]['pol'] + "</td>";
                row+="<td>" + data[i]['datumRodjenja'] + "</td>";
                row+="<td>" + data[i]['uloga'] + "</td>";

                $('#sviKorisnici').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});


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
                row+="<td>" + data[i]['naziv'] + "</td>";
                row+="<td>" + data[i]['tip'] + "</td>";
                row+="<td>" + data[i]['lokacija'] + "</td>";

                $('#sviRestorani').append(row);
            }
        },
        error:function (data){
            console.log("GRESKA:",data)
        }
    });
});

$(document).on("submit","#postaviM",function (event){
    event.preventDefault();

    var naziv = $("#nazivMen").val();
    var tip = $("#nazivRes").val();

    var noviRestoran = formToJson1000(naziv,tip);

    $.ajax(
        {
            type:"POST",
            url:"http://localhost:8080/api/admin/postavi-menadzera",
            dataType:"json",
            contentType:"application/json",
            data:noviRestoran,
            success:function()
            {
                alert("Menadzer uspesno postavljen!");
            },
            error:function (data)
            {
                alert("Greska kod postavke menadzera!");
            }
        }
    );
});

function formToJson1000(ki,loz)
{
    return JSON.stringify(
        {
            "menadzer":ki,
            "restoran":loz
        }
    );
}