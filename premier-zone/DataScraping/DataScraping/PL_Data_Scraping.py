import pandas as pd
import requests
from bs4 import BeautifulSoup
import time

all_teams = []  # list to store all teams

# Hämta HTML från sidan
html = requests.get('https://fbref.com/en/comps/9/Premier-League-Stats').text

# Skriver ut HTML för att se om sidan hämtas korrekt
print(html[:1000])  # För att bara visa de första 1000 tecknen, så vi inte får ett superlångt utskrift

# Parsar HTML
soup = BeautifulSoup(html, 'lxml')

# Försök att hitta alla tabeller med klassen 'stats_table'
tables = soup.find_all('table', class_="stats_table")
print(f"Found {len(tables)} tables")  # Visar antalet tabeller som hittades

# Om ingen tabell hittas, skriv ut ett meddelande och avsluta
if len(tables) == 0:
    print("Ingen tabell hittades på sidan!")
else:
    # Hämtar den första tabellen om den finns
    table = tables[0]

    # Hämta alla länkar i tabellen
    links = table.find_all('a')
    links = [l.get("href") for l in links]
    links = [l for l in links if '/squads/' in l]  # Filtrerar för att bara få länkar till lag

    # Formatera om länkarna till kompletta URL:er
    team_urls = [f"https://fbref.com{l}" for l in links]

    # Loopa genom varje lags URL för att hämta statistik
    for team_url in team_urls:
        team_name = team_url.split("/")[-1].replace("-Stats", "")  # Isolera lagets namn
        data = requests.get(team_url).text
        soup = BeautifulSoup(data, 'lxml')

        # Försök att hitta den första tabellen för lagstatistik
        stats = soup.find_all('table', class_="stats_table")

        # Kontrollera om vi hittar några tabeller
        if stats:
            stats = stats[0]  # Ta den första tabellen om den finns
        else:
            print(f"Ingen statistiktabell hittades för {team_name}!")
            continue  # Om ingen statistik hittas för laget, gå vidare till nästa lag

        # Om tabellen har flera nivåer (t.ex. multi-index), ta bort överflödig nivå
        if stats and stats.columns:
            stats.columns = stats.columns.droplevel()

        # Konvertera BeautifulSoup-tabellen till en Pandas DataFrame
        team_data = pd.read_html(str(stats))[0]
        team_data["Team"] = team_name  # Lägg till lagets namn i DataFrame

        all_teams.append(team_data)  # Lägg till lagets statistik i listan

        time.sleep(5)  # Vänta 5 sekunder för att inte bli blockerad av webbplatsen

    # Kombinera alla lags data i en enda DataFrame
    stat_df = pd.concat(all_teams)

    # Spara data till CSV-fil
    stat_df.to_csv("stats.csv", index=False)

    print("Data scraping completed and saved to 'stats.csv'")
