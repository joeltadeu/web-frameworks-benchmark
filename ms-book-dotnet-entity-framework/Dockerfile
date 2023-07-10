FROM mcr.microsoft.com/dotnet/sdk:6.0-alpine as build
WORKDIR /app
COPY *.csproj ./
RUN dotnet restore
COPY . ./
RUN dotnet publish -o /app/published-app

FROM mcr.microsoft.com/dotnet/aspnet:6.0-alpine as runtime
WORKDIR /app

EXPOSE 8080/tcp
ENV ASPNETCORE_URLS http://*:8080

COPY --from=build /app/published-app /app
ENTRYPOINT ["dotnet", "/app/ms-book-dotnet-entity-framework.dll"]