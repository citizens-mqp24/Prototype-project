FROM node:22-alpine
WORKDIR /app
LABEL authors="nickl"
COPY . .

RUN npm install
RUN npm install serve
RUN npm run build

EXPOSE 3000

ENTRYPOINT npm run serve