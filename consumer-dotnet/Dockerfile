FROM microsoft/dotnet:sdk

WORKDIR /app

COPY . .

RUN dotnet publish -c Release -o out

CMD [ "dotnet", "out/consumer-dotnet.dll" ]
