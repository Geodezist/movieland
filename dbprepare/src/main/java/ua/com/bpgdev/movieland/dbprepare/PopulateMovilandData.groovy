package ua.com.bpgdev.movieland.dbprepare

class PopulateMovilandData {

    void populateSimpleDimension(String tableName, String dimensionFileName) {
        File sourceFile = new File(dimensionFileName)
        String sqlFileName = dimensionFileName.substring(0, dimensionFileName.length() - 3) + "sql"
        File sqlFile = new File(sqlFileName)
        sqlFile << "SET search_path TO movieland;\n"

        def lines = sourceFile.readLines()
        lines.each { String line ->
            line = line.replace "'", "''"
            String sqlLine = "INSERT INTO ${tableName} (title) VALUES ('${line}');"
            sqlFile << sqlLine + "\n"
        }
        sqlFile << "COMMIT;" + "\n"
    }

    void populateMovie(String movieFileName) {
        File sourceFile = new File(movieFileName)
        String sqlFileName = movieFileName.substring(0, movieFileName.length() - 3) + "sql"
        File sqlFile = new File(sqlFileName)
        sqlFile << "SET search_path TO movieland;\n"

        def lines = sourceFile.readLines()
        int counter = 0
        Map columnMap = new HashMap()

        lines.each { String line ->
            line = line.replace "'", "''"
            if (counter == 0) {
                String[] titles = line.split("/")
                columnMap.put("title", ("'" + titles[0] + "'"))
                columnMap.put("title_original", ("'" + titles[1] + "'"))
            } else if (counter == 1) {
                columnMap.put("year", line.toInteger())
            } else if (counter == 2) {
                columnMap.put("country", line.split(", ") as ArrayList<String>)
            } else if (counter == 3) {
                columnMap.put("genre", line.split(", ") as ArrayList<String>)
            } else if (counter == 4) {
                columnMap.put("description", ("'" + line + "'"))
            } else if (counter == 5) {
                columnMap.put("rating", line.replace("rating:", "").toDouble())
            } else if (counter == 6) {
                columnMap.put("price", line.replace("price:", "").toDouble())
            } else if (line.isEmpty() && counter != 0) {
                String sqlLine = "INSERT INTO movie (title, title_original, release_year, description, rating, price)\n" +
                        "SELECT \n" +
                        "   ${columnMap.get("title")},\n" +
                        "   ${columnMap.get("title_original")},\n" +
                        "   ${columnMap.get("year")},\n" +
                        "   ${columnMap.get("description")},\n" +
                        "   ${columnMap.get("rating")},\n" +
                        "   ${columnMap.get("price")}\n" +
                        ";\n"
                String sqlAddGenre = ""
                for (String genre in columnMap.get("genre")) {
                    sqlAddGenre = sqlAddGenre + "INSERT INTO ref_movie_genre (movie_id, genre_id)\n" +
                            "SELECT \n" +
                            "   (SELECT id FROM movie WHERE title = ${columnMap.get("title")}), \n" +
                            "   (SELECT id FROM d_genre WHERE title = '${genre}')\n" +
                            ";\n"
                }

                String sqlAddCountry = ""
                for (String country in columnMap.get("country")) {
                    sqlAddCountry = sqlAddCountry + "INSERT INTO ref_movie_country (movie_id, country_id)\n" +
                            "SELECT \n" +
                            "   (SELECT id FROM movie WHERE title = ${columnMap.get("title")}), \n" +
                            "   (SELECT id FROM d_country WHERE title = '${country}')\n" +
                            ";\n"
                }

                sqlFile << sqlLine + "\n"
                sqlFile << sqlAddGenre + "\n"
                sqlFile << sqlAddCountry + "\n"
                sqlFile << "\n\n"
                columnMap = new HashMap()
                counter = -1
            }
            counter++
        }
        sqlFile << "COMMIT;" + "\n"
    }


    void populatePoster(String posterFileName) {
        File sourceFile = new File(posterFileName)
        String sqlFileName = posterFileName.substring(0, posterFileName.length() - 3) + "sql"
        File sqlFile = new File(sqlFileName)
        sqlFile << "SET search_path TO movieland;\n"

        def lines = sourceFile.readLines()
        lines.each { String line ->
            String[] valuesLine = line.split(" http")
            String movieTitle = valuesLine[0].replace("'", "''")
            String posterURL = valuesLine[1].replace("'", "''")
            String sqlLine = "INSERT INTO movie_poster (movie_id, poster_url)" +
                    "SELECT\n" +
                    "   (SELECT id FROM movie WHERE title ='${movieTitle}'),\n" +
                    "   'http${posterURL}'\n" +
                    ";\n"
            sqlFile << sqlLine + "\n"
        }
        sqlFile << "COMMIT;" + "\n"
    }
}
