package main

import (
	"github.com/gorilla/mux"
	_ "github.com/jinzhu/gorm/dialects/mysql"
	"log"
	"ms-book-go-mux-gorm/controller"
	"ms-book-go-mux-gorm/database"
	"net/http"
)

func main() {
	initDB()
	log.Println("Starting the HTTP server on port 8080")

	router := mux.NewRouter().StrictSlash(true)
	initaliseHandlers(router)
	log.Fatal(http.ListenAndServe(":8080", router))
}

func initaliseHandlers(router *mux.Router) {
	router.HandleFunc("/v1/books", controller.GetAllBook).Methods("GET")
	router.HandleFunc("/v1/books/{id}", controller.GetBookByID).Methods("GET")
}

func initDB() {
	config :=
		database.Config{
			ServerName: "book-mysql-db:3306",
			User:       "root",
			Password:   "root",
			DB:         "performance-books",
		}

	connectionString := database.GetConnectionString(config)
	err := database.Connect(connectionString)
	if err != nil {
		panic(err.Error())
	}
}
