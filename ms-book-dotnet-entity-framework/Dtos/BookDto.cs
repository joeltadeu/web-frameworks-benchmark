namespace ms_book_dotnet_entity_framework.Dtos;

public class BookDto
{
    public int Id { get; set; }

    public string Title { get; set; }

    public string Isbn { get; set; }

    public int LanguageId { get; set; }

    public int NumPages { get; set; }
    
    public DateOnly PublicationDate { get; set; }
    
    public int PublisherId { get; set; }
}