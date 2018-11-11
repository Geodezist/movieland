package ua.com.bpgdev.movieland.dbprepare
app = new PopulateMovilandData()
//app.hello()
app.populateSimpleDimension("d_country", "C:/WORK/DEVELOP/JavaProjects/Java Basics Luxoft/movieland/dbprepare/src/main/resources/country.txt")
app.populateSimpleDimension("d_genre","C:/WORK/DEVELOP/JavaProjects/Java Basics Luxoft/movieland/dbprepare/src/main/resources/genre.txt")
app.populateMovie("C:/WORK/DEVELOP/JavaProjects/Java Basics Luxoft/movieland/dbprepare/src/main/resources/movie.txt")
app.populatePoster("C:/WORK/DEVELOP/JavaProjects/Java Basics Luxoft/movieland/dbprepare/src/main/resources/poster.txt")
