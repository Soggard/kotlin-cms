<html>
<head>
    <title>Editer un article</title>
</head>
<body>
<h1>Editer un article</h1>
<form action="/edit" method="post">
    <input type="hidden" name="id" value="${article.id}">
    <p>
        <label>
            Titre
            <input type="text" name="title" value="${article.title}">
        </label>
    </p>
    <p>
        <label>
            Contenu
            <textarea name="text">${article.text}</textarea>
        </label>
    </p>
    <input type="submit" value="Editer l'article">
</form>
</body>
</html>