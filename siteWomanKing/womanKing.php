<?php
/*
 * On indique que les chemins des fichiers qu'on inclut
 * seront relatifs au répertoire src.
 */
set_include_path("./src");

/* Inclusion des classes utilisées dans ce fichier */

require_once("Router.php");
require_once("model/CastingStorageFile.php");
require_once("model/CastingStorageMySQL.php");
require_once('/users/22009614/private/mysql_config.php');

/*
 * Cette page est simplement le point d'arrivée de l'internaute
 * sur notre site. On se contente de créer un routeur
 * et de lancer son main.
 */
//$router = new Router(new CastingStorageFile("/users/22009614/tmp/castingDataFile.txt"));


$dsn = 'mysql:host='. MYSQL_HOST.';port='.MYSQL_PORT. ';dbname='. MYSQL_DB.';charset=utf8mb4';
$pdo = new PDO($dsn, MYSQL_USER, MYSQL_PASSWORD); 

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$router  = new Router(new CastingStorageMySQL($pdo));

$router->main();
?>