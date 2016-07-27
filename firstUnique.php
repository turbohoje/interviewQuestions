<?php
/*
return the first unique character in a string.
*/
error_reporting(E_ALL ^ E_NOTICE); //suppress the empty index thing

$string = "aababbasldfasdfasdfasddfasdfasdf";

$counts = array();
$firstOccurence = array();

for($i = 0; $i < strlen($string); $i++){
    $character = substr($string, $i, 1); //pull a character out
    $counts[$character] += 1;
    if($counts[$character] == 1){
        $firstOccurence[$character] = $i;
    }
}

$lowestIndex = strlen($string);
$winner = "";
foreach($counts as $char => $count){
    if($count == 1){
        if($firstOccurence[$char] < $lowestIndex){
            $lowestIndex = $firstOccurence[$char];
            $winner = $char;
        }
    }
}

if($winner == ""){
    print "No match";
}
else{
    print "winner: $winner";
}

?>

