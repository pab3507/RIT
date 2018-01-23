<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>wm2Edit</title>
    <style type="text/css">
        /* Styling this page is up to you. */
    </style>
    <script type="text/javascript">
        /* You might want to add a script to prevent users from
            submitting an empty form. */
    </script>
    <?php include "../php_ice/reusable.php"; ?>
</head>
<body>
    <h1>wm2 Simple Editor</h1>
    <form action="../php_ice/index.php" method="POST">
        <textarea name="news" cols="80" rows="24"><?php FetchAndEcho('../php_ice/news.inc'); ?></textarea>
        <br /><input type="reset" value="Reset" /> <input type="submit" value="Save Text" />
    </form>
</body>
</html>
