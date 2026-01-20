package entity

import (
	"ms-book-go-mux-gorm/types"
)

type Book struct {
	ID              int        `json:"id"`
	Title           string     `json:"title"`
	Isbn            string     `json:"isbn"`
	PublicationDate types.Date `json:"publication_date"`
	LanguageId      int        `json:"language_id"`
	NumPages        int        `json:"num_pages"`
	PublisherId     int        `json:"publisher_id"`
}

type Tabler interface {
	TableName() string
}

// TableName overrides the table name used by User to `profiles`
func (Book) TableName() string {
	return "book"
}
