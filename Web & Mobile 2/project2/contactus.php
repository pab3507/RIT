<?php
if(isset($_POST['submit']))
{

$message=
'Full Name:	'.$_POST['fullname'].'<br />
Subject:	'.$_POST['subject'].'<br />
Phone:	'.$_POST['phone'].'<br />
Email:	'.$_POST['emailid'].'<br />
Comments:	'.$_POST['comments'].'
';
    require "assets/phpmailer/class.phpmailer.php"; //include phpmailer class

    // Instantiate Class
    $mail = new PHPMailer();

    // Set up SMTP
    $mail->IsSMTP();                // Sets up a SMTP connection
    $mail->SMTPAuth = true;         // Connection with the SMTP does require authorization
    $mail->SMTPSecure = "ssl";      // Connect using a TLS connection
    $mail->Host = "smtp.gmail.com";  //Gmail SMTP server address
    $mail->Port = 465;  //Gmail SMTP port
    $mail->Encoding = '7bit';

    // Authentication
    $mail->Username   = "emailsendermalp@gmail.com"; // Your full Gmail address
    $mail->Password   = "emailsender1234"; // Oh No! You've seen my password :(

    // Compose
    $mail->SetFrom($_POST['emailid'], $_POST['fullname']);
    $mail->AddReplyTo($_POST['emailid'], $_POST['fullname']);
    $mail->Subject = "New Contact Form Message";      // Subject (which isn't required)
    $mail->MsgHTML($message);

    // Send To
    $mail->AddAddress("mal3941@g.rit.edu", "Recipient Name"); // Where to send it - Recipient
    $result = $mail->Send();		// Send!
  	$message = $result ? 'Successfully Sent!' : 'Sending Failed!';
  	unset($mail);

}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>

		<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">

		<title>Contact Us</title>
		<!-- Header file included containing navbar-->
		<?php include ("inc/header.php"); ?>
		<div class="container-fluid">
      <div class="row">
        <h1>Contact Us</h1>
        <hr>
        <div class="col-md-4">
          <h3> Contact Form </h3>
          <form name="form1" id="form1" action="" method="post">
      				<fieldset>
                <div class="form-group">
                  <input type="text" class="form-control" name="fullname" placeholder="Full Name" />
                </div>
                <div class="form-group">
      				    <input class="form-control" type="text" name="subject" placeholder="Subject" />
                </div>
                <div class="form-group">
      				    <input class="form-control" type="text" name="phone" placeholder="Phone" />
                </div>
                <div class="form-group">
      				    <input class="form-control" type="text" name="emailid" placeholder="Email" />
                </div>
                <div class="form-group">
      				    <textarea class="form-control" rows="4" cols="20" name="comments" placeholder="Comments"></textarea>
                </div>
      				  <input class="btn btn-block" type="submit" name="submit" value="Send" />
      				</fieldset>
      		</form>
          <p><?php if(!empty($message)) echo $message; ?></p>
        </div>
        <div class="col-md-4">
					<h2> Moisés Lora Pérez</h2>
					<h3> Software Engineering Student </h3>
          <p>Hardworking, dedicated, responsible individual with a passion for technology and coding. Enthusiast for overcoming challenges
             and obstacles in life. Bilingual in English & Spanish, with strong leadership and interpersonal skills looking to grow and succeed
              in all aspects of life. Currently seeking a Cooperative Education/Internship Opportunity during Summer/Fall 2018.
              <p> Feel free to contact me anytime through the contact form or my email: <b><a href="mailto:mal3941@rit.edu">mal3941@rit.edu</a></b> </p>
        </div>
        <div class="col-md-4">
					<img class="img-circle img-responsive" src="assets/media/portrait.jpg">
				</div>
				<!-- End of Column-->
        </div>
				<!-- End of Container-->
      </div>
			<!-- End of row-->
	<!-- Footer file included-->
  <?php include ("inc/footer.php"); ?>
