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
