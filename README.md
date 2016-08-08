# FastDownloader
It's an aplication built in Java to download files at lightening speed!

##Features
1. Multiple Sources: Single or Multiple sources for the file could be provided. In that way, download would be faster as segements of the file would be downloaded paralelly from multiple sources.(Acts like torrent)
2. Resume Download: Suppose the jmv crashes or user exits the program abdruptly, and upon restart, application would resume  download and doesn't download the file from start.
3. More efficiency: Upon setting thread property to optimum, it will compute the optimum number of threads for more efficiency.

##Limitations
1. Only files over http could be downloaded.
2. If the server doesn't support partial download, sequential download is carried out.

##Improvements
1. GUI
2. Facilitate other protocols

