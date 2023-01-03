<!DOCTYPE html>
<html lang=fr>
        
<head> 
<title><?php echo "{$this->headTitle}";?> </title>

<link rel="stylesheet" href="https://dev-22009614.users.info.unicaen.fr/2022/TW4/tp6/src/css/style.css"/>

</head>

<body>
    <p>
        <?php echo "{$this->feedback}";?>
    </p>
    <nav>
		<ul>
            <?php
            foreach ($this->menu as $intitule => $lien) {
	                echo "<li><a href=\"$lien\">$intitule</a></li>";
            }
            ?>
		</ul>
	</nav>

    <br>

    <h1 id="title" > 
        <?php 
            if($this->title !== null){
                echo "{$this->title}";
            }
        ?>
    </h1>

    <br>

    <h4> 
        <?php 
            if($this->contentRole!== null){
                echo "{$this->contentRole}";
            }
        ?>
    </h4>

    <h4> 
        <?php 
            if($this->contentAge !== null){
                echo "{$this->contentAge}";
            }
        ?>
    </h4>

    <div id="discover"> 
        <?php 
            if($this->image !== null){
                echo "{$this->image}";
            }
            echo "<div>";
            if($this->synopsis !== null){
                //echo "{$this->synopsis}";
                echo "<p id='synopsis'>{$this->synopsis} </p>";
            }
            if($this->imageAmazones !== null){
                //echo "{$this->synopsis}";
                echo "<div id='amazone'>{$this->imageAmazones} </div>";
            } 
            echo "</div>"; 
        ?>

    </div>

    <div id="forms">
        <?php 
            if($this->form !== null){
                echo "{$this->form}";
            }
        ?>
    </div>
    
    

    
</body>

</html>
