$filePath = "????????"

while ($true) {
    $pingResult = ping perf.siimon.ee -n 1 | Select-String "Average"
    $pingTime = Get-Date

    if ($pingResult) {
        $pingResult = $pingResult.ToString().Split("=")[1].Trim()
        $output = [PSCustomObject]@{
            Time = $pingTime
            Result = $pingResult
        }
        $output | Export-Csv -Path $filePath -Append -NoTypeInformation
    }
    Start-Sleep -Seconds 5
}
