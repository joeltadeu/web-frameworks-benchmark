package types

import (
	"database/sql"
	"time"
)

type Date string

func (t *Date) Scan(src interface{}) error {
	s, ok := src.(time.Time)
	if !ok {
		return nil
	}
	*t = Date(s.Format("2006-01-02"))
	return nil
}

// Data implements the below interfaces to satisfy both
// json parser and sql parser

var _ sql.Scanner = (*Date)(nil)
