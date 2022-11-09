CREATE TABLE "like_author"(
                              "author_id" INTEGER NOT NULL,
                              "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_author" ADD PRIMARY KEY("author_id");
CREATE TABLE "User"(
                       "User_id" INTEGER NOT NULL,
                       "username" VARCHAR(65036) NOT NULL,
                       "avatar" VARCHAR(65036) NOT NULL,
                       "email" VARCHAR(65036) NOT NULL,
                       "role" VARCHAR(65036) NOT NULL
);
ALTER TABLE
    "User" ADD PRIMARY KEY("User_id");
CREATE TABLE "like_publications"(
                                    "publication_id" INTEGER NOT NULL,
                                    "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_publications" ADD PRIMARY KEY("publication_id");
CREATE TABLE "like_blocks"(
                              "block_id" INTEGER NOT NULL,
                              "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_blocks" ADD PRIMARY KEY("block_id");
CREATE TABLE "publication"(
                              "publication_id" INTEGER NOT NULL,
                              "author_id" INTEGER NOT NULL
);
ALTER TABLE
    "publication" ADD PRIMARY KEY("publication_id");
CREATE TABLE "block"(
                        "block_id" INTEGER NOT NULL,
                        "base64" VARCHAR(65036) NOT NULL,
                        "x" INTEGER NOT NULL,
                        "y" INTEGER NOT NULL
);
ALTER TABLE
    "block" ADD PRIMARY KEY("block_id");
CREATE TABLE "blockspublication"(
                                    "publication_id" INTEGER NOT NULL,
                                    "block_id" INTEGER NOT NULL
);
ALTER TABLE
    "blockspublication" ADD PRIMARY KEY("publication_id");
ALTER TABLE
    "publication" ADD CONSTRAINT "publication_author_id_foreign" FOREIGN KEY("author_id") REFERENCES "like_author"("author_id");
ALTER TABLE
    "like_publications" ADD CONSTRAINT "like_publications_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "like_blocks" ADD CONSTRAINT "like_blocks_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "like_author" ADD CONSTRAINT "like_author_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "blockspublication" ADD CONSTRAINT "blockspublication_block_id_foreign" FOREIGN KEY("block_id") REFERENCES "block"("block_id");
CREATE TABLE "like_author"(
                              "author_id" INTEGER NOT NULL,
                              "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_author" ADD PRIMARY KEY("author_id");
CREATE TABLE "User"(
                       "User_id" INTEGER NOT NULL,
                       "username" VARCHAR(65036) NOT NULL,
                       "avatar" VARCHAR(65036) NOT NULL,
                       "email" VARCHAR(65036) NOT NULL,
                       "role" VARCHAR(65036) NOT NULL
);
ALTER TABLE
    "User" ADD PRIMARY KEY("User_id");
CREATE TABLE "like_publications"(
                                    "publication_id" INTEGER NOT NULL,
                                    "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_publications" ADD PRIMARY KEY("publication_id");
CREATE TABLE "like_blocks"(
                              "block_id" INTEGER NOT NULL,
                              "user_id" INTEGER NOT NULL
);
ALTER TABLE
    "like_blocks" ADD PRIMARY KEY("block_id");
CREATE TABLE "publication"(
                              "publication_id" INTEGER NOT NULL,
                              "author_id" INTEGER NOT NULL
);
ALTER TABLE
    "publication" ADD PRIMARY KEY("publication_id");
CREATE TABLE "block"(
                        "block_id" INTEGER NOT NULL,
                        "base64" VARCHAR(65036) NOT NULL,
                        "x" INTEGER NOT NULL,
                        "y" INTEGER NOT NULL
);
ALTER TABLE
    "block" ADD PRIMARY KEY("block_id");
CREATE TABLE "blockspublication"(
                                    "publication_id" INTEGER NOT NULL,
                                    "block_id" INTEGER NOT NULL
);
ALTER TABLE
    "blockspublication" ADD PRIMARY KEY("publication_id");
ALTER TABLE
    "publication" ADD CONSTRAINT "publication_author_id_foreign" FOREIGN KEY("author_id") REFERENCES "like_author"("author_id");
ALTER TABLE
    "like_publications" ADD CONSTRAINT "like_publications_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "like_blocks" ADD CONSTRAINT "like_blocks_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "like_author" ADD CONSTRAINT "like_author_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "User"("User_id");
ALTER TABLE
    "blockspublication" ADD CONSTRAINT "blockspublication_block_id_foreign" FOREIGN KEY("block_id") REFERENCES "block"("block_id");
ALTER TABLE
    "like_blocks" ADD CONSTRAINT "like_blocks_block_id_block_id_foreign" FOREIGN KEY ("block_id") REFERENCES "block"("block_id");
ALTER TABLE
    "blockspublication" ADD CONSTRAINT "blockspublication_publication_id_foreign" FOREIGN KEY ("publication_id") REFERENCES "publication"("publication_id");