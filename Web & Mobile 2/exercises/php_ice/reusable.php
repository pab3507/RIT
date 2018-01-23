<?php
    function FetchAndEcho($pv_FilePathAndName) {
        $fv_FileHandle = fopen($pv_FilePathAndName,'r');
        if ($fv_FileHandle) {
            flock($fv_FileHandle, LOCK_SH);
                echo file_get_contents($pv_FilePathAndName);
            flock($fv_FileHandle, LOCK_UN);
        } else {
            echo "No News Right Now";
        }
        fclose($fv_FileHandle);
    }

    function SaveText($pv_FilePathAndName, $pv_TheText) {
        $fv_FileHandle=fopen($pv_FilePathAndName,'w');
        flock($fv_FileHandle, LOCK_EX);
            fwrite($fv_FileHandle,  $pv_TheText);
        flock($fv_FileHandle, LOCK_UN);
        fclose($fv_FileHandle);
    }
?>
