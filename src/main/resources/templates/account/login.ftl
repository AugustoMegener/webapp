<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
</head>
<body>
    <form action="/account/login?form=login" method="post">
        <label for="email">E-mail</label><input type="email" name="email" id="email">
        <label for="password">Senha</label><input type="password" name="password" id="password">
        <input type="submit" value="Logar">
        <small>${loginInfo}</small>
    </form>
    <form action="/account/login?form=signin" method="post">
        <label for="name">Nome</label><input type="text" name="name" id="name">
        <label for="birthday">Data de nascimento</label><input type="date" name="birthday" id="birthday">
        <label for="email">E-mail</label><input type="email" name="email" id="email">
        <label for="password">Senha</label><input type="password" name="password" id="password">
        <input type="submit" value="Criar conta">
        <small>${signingInfo}</small>
    </form>
</body>
</html>
