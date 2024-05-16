<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creer un compte</title>
    <link rel="stylesheet" href="css/signup.css">

</head>
<body>
    <main>
        <div class="logo-container">
            <img src="assets/LogoDocMaster.svg" alt="Logo DocMaster" class="logo">
        </div>
        <div class="container">
            <section>
                <form action="signup.do" method="post">
                   
                    <div>
                        <input  placeholder="Username"type="text" id="username" name="username" required aria-label="username">
                    </div>
                    <div>
                        <input  placeholder="password" type="password" id="password" name="password" required aria-label="Mot de passe">
                    </div><div>
                        <input  placeholder="confirm password" type="password" id="confirmed_password" name="confirmed_password" required aria-label="Mot de passe">
                    </div>
                    <button id="submit-btn" type="submit">Creer un compte</button>
                </form>
                <p>
                    <a href="signin.jsp">vous-avez Deja  un compte? Connectez-vous</a>
                </p>
            </section>
        </div>
        <%= request.getAttribute("error") != null ? "Une erreur" : "" %>
    </main>
     <script type="text/javascript">
 $(document).ready(function(){

	 $("#submit-button").click(function(){
		 var username=$("username").val();
		  localStorage.setItem('username',username);
			
	 });
	 
	 
 })
 	</script>
</body>
</html>
