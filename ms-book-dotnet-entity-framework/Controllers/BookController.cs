using ms_book_dotnet_entity_framework.Dtos;
using ms_book_dotnet_entity_framework.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Net;

namespace ms_book_dotnet_entity_framework.Controllers;

[ApiController]
[Route("v1/books")]
public class BookController : ControllerBase
{
    private readonly DBContext DBContext;

    public BookController(DBContext DBContext)
    {
        this.DBContext = DBContext;
    }

    [HttpGet("")]
    public async Task<ActionResult<List<BookDto>>> FindAll()
    {
        var List = await DBContext.Books.Select(
            s => new BookDto
            {
                Id = s.Id,
                Title = s.Title,
                Isbn = s.Isbn,
                LanguageId = s.LanguageId,
                NumPages = s.NumPages,
                PublicationDate = s.PublicationDate,
                PublisherId = s.PublisherId
            }
        ).ToListAsync();

        if (List.Count < 0)
        {
            return NotFound();
        }
        else
        {
            return List;
        }
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<BookDto>> FindById(int id)
    {
        BookDto? Book = await DBContext.Books.Select(
            s => new BookDto
            {
                Id = s.Id,
                Title = s.Title,
                Isbn = s.Isbn,
                LanguageId = s.LanguageId,
                NumPages = s.NumPages,
                PublicationDate = s.PublicationDate,
                PublisherId = s.PublisherId
            })
        .FirstOrDefaultAsync(s => s.Id == id);

        if (Book == null)
        {
            return NotFound();
        }
        else
        {
            return Book;
        }
    }
}