<?php  //Must create a session BEFORE headers are sent. 
		// Why do you think this is so?
	
	session_name('WIA_ICE'); // Change this name to something unique!
							// (Alphanumeric characters and underlines only.)
							// Remember, you are all sharing the same server,
							// and by default the same session ID.  Since you will
							// likely be using the same variable names this could be
							// very troublesome. ;-)
	session_start();
	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="content-type" content="application/xhtml+xml; charset=utf-8" />
	<title>Sesstion Fun</title>
</head>
<body>
<?php
	// Did we get a form submission?
	if (isset($_POST['t_MagicWord'])) {
		// Now let's set that session variable
		$_SESSION['s_MagicWord'] = $_POST['t_MagicWord'];
	}
//check to see if we've got an active session:
if (!isset($_SESSION['s_MagicWord'])) {
	//first time here this session, so display a form
	echo "<form method=\"POST\" action=\"" . $_SERVER['PHP_SELF'] . "\">\n";
	echo '	<table border="0">
			<tr>
				<td>
					<b>Magic Word:</b> <input type="text" name="t_MagicWord">
					<br /><br />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">
					<input type="Submit" Value="Set the Word">
				</td>
			</tr>
		</table>
	</form>';
	
} else {
	// display some feedback for the user
	
	echo "<h2>The magic word is: " . $_SESSION['s_MagicWord']."!</h2>\n";
	echo "<p>Here is a link to the rest of our site:</p>\n";
	echo '<blockquote><a href="thesite.php">The rest of our site</a></blockquote>';
}

?>
</body>
</html>