-- Table: public.Records

-- DROP TABLE IF EXISTS public."Records";

CREATE TABLE IF NOT EXISTS public."Records"
(
    "recordID" bigint NOT NULL,
    "patientID" bigint NOT NULL,
    "hospitalName" text COLLATE pg_catalog."default" NOT NULL,
    ethnicity text COLLATE pg_catalog."default",
    age smallint,
    sex boolean,
    cholecterol real,
    "WBC" real,
    "NE" real,
    "LY" real,
    "MO" real,
    "EO" real,
    "BA" real,
    CONSTRAINT "Records_pkey" PRIMARY KEY ("recordID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Records"
    OWNER to "myUser";