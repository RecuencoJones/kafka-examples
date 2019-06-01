FROM ruby:2.6

WORKDIR /app

COPY . /app

RUN gem install bundler \
 && bundle install

CMD [ "ruby", "main.rb" ]
