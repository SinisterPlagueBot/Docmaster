<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="css/signin.css">
</head>
<body>
	<div class="logo-container">
		<img src="assets/LogoDocMaster.svg" alt="Logo DocMaster" class="logo">
	</div>
	<div class="container">

		<form action="signin.do" method="post">
			<input id="username"type="text" name="username" placeholder="Username" required>
			<input type="password" name="password" placeholder="Password"
				required>
			<button id="submit-btn"type="submit" style="display: block">Connexion</button>
			<%-- Display error message if available --%>

			<a href="signup.jsp"> Pas de compte? Créez un nouveau compte</a>
		</form>

	</div>
	<%= request.getAttribute("error") != null ? "Une erreur" : "" %>
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
