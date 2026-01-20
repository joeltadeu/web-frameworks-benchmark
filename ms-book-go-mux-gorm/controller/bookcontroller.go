package controller

import (
	"encoding/json"
	_ "io/ioutil"
	"ms-book-go-mux-gorm/database"
	"ms-book-go-mux-gorm/entity"
	"net/http"
	_ "strconv"

	"github.com/gorilla/mux"
)

// GetAllBooks get all books data
func GetAllBook(w http.ResponseWriter, r *http.Request) {
	var books []entity.Book

	database.Connector.Find(&books)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(books)
}

// GetBookByID returns book with specific ID
func GetBookByID(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	key := vars["id"]

	var book entity.Book
	database.Connector.First(&book, key)
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(book)
}
