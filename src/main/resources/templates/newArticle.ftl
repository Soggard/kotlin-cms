<html>
<head>
    <title>Publier un article</title>
</head>
<body>
    <h1>Publier un article</h1>
    <form action="/new" method="post">
        <p>
            <label>
                Titre
                <input type="text" name="title">
            </label>
        </p>
        <p>
            <label>
                Contenu
                <textarea name="text"></textarea>
            </label>
        </p>
        <input type="submit" value="Créer l'article">
    </form>
</body>
</html>