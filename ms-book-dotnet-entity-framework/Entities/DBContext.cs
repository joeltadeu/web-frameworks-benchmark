using Microsoft.EntityFrameworkCore;

namespace ms_book_dotnet_entity_framework.Entities;

public partial class DBContext : DbContext
    {
        public DBContext()
        {
        }

        public DBContext(DbContextOptions<DBContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Book> Books { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseMySQL("server=book-mysql-db;port=3306;user=root;password=root;database=performance-books;Connect Timeout=5000;SslMode=none");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Book>(entity =>
            {
                entity.ToTable("book");

                entity.Property(e => e.Id).HasColumnType("int(11)");

                entity.Property(e => e.Title)
                    .IsRequired()
                    .HasMaxLength(400);

                entity.Property(e => e.Isbn)
                    .IsRequired()
                    .HasMaxLength(13);

                entity.Property(e => e.LanguageId)
                    .IsRequired()
                    .HasColumnName("language_id")
                    .HasColumnType("int(11)");

                entity.Property(e => e.NumPages)
                    .IsRequired()
                    .HasColumnName("num_pages")
                    .HasColumnType("int(11)");

                entity.Property(e => e.PublisherId)
                    .IsRequired()
                    .HasColumnName("publisher_id")
                    .HasColumnType("int(11)");

                entity.Property(e => e.PublicationDate)
                    .IsRequired()
                    .HasColumnName("publication_date");    
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }