<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>WM2 News</title>
    <style type="text/css">
        /* Styling this page is up to you. */
    </style>
    <?php include "../php_ice/reusable.php"; ?>
</head>
<body>
    <h1>WM2 News!</h1>
    <p>
<?php
    if ($_POST['news']) {
      SaveText('news.inc', htmlspecialchars($_POST['news']));    }
    FetchAndEcho('news.inc');
?>
    </p>
</body>
</html>
