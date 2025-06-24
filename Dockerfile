FROM ubuntu:latest
LABEL authors="gajuq"

ENTRYPOINT ["top", "-b"]