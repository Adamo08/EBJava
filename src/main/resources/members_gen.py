import csv
from faker import Faker

# Initialize Faker to generate random data
fake = Faker()

# Generating random data for 20 people
random_data = [
    [fake.last_name(), fake.first_name(), fake.email(), fake.phone_number()]
    for _ in range(20)
]

# Defining CSV file path
file_path = 'members.csv'

# Writing data to CSV file
with open(file_path, mode='w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    # Write the header
    writer.writerow(["Nom", "Prénom", "Email", "Phone"])
    # Write random data rows
    writer.writerows(random_data)

print("CSV file 'members.csv' has been generated.")
